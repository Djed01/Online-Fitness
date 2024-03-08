import { Component, OnInit } from '@angular/core';
import { Program } from '../models/program.model';
import { ProgramService } from '../services/program.service';
import { Router } from '@angular/router';
import { NewsItem } from '../models/newsItem.model';
import { HttpClient } from '@angular/common/http';
import { NewsService } from '../services/news.service';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrls: ['./programs.component.css'] 
})
export class ProgramsComponent implements OnInit {
  programs: Program[] = [];
  filteredPrograms: Program[] = [];
  newsItems: NewsItem[] = [];
  photo: string = 'assets/images/gym.jpg';
  currentPage: number = 1;
  programsPerPage: number = 10;
  totalPrograms: number = 0;
  searchTerm: string = '';
  showScroll: boolean = true;

  constructor(
     private programService: ProgramService,
     private router: Router,
     private http: HttpClient,
     private newsService: NewsService,
     private authService: AuthService,
     private route: ActivatedRoute,
     private imageService:ImageService) {
      this.activate();
     }

  ngOnInit() {
    this.loadPrograms();
    this.fetchNews();
  }

  loadPrograms() {
    this.programService.getAllByStatusPrograms().subscribe((data: Program[]) => {
      this.programs = data;
      for (var program of this.programs) {
        if (program.images && program.images.length > 0 && program.images[0].id) {
          this.imageService.downloadImage(program.images[0].id).subscribe((url: string) => {
            console.log(url);
            program.url = url;
          },
            error => {
              console.error('Error occurred during fetching images:', error);
              // Handle error as needed
            }
          );
        } else {
          program.url = this.photo; // Set a default photo if no image is available
        }
      }
      console.log(data);
      this.totalPrograms = this.programs.length;
      this.applyFilters();
    });
  }
  

  onPageChange(pageNumber: number) {
    this.currentPage = pageNumber;
    this.applyFilters();
  }

  onSearchChange() {
    this.currentPage = 1;
    this.applyFilters();
  }

  applyFilters() {
    // Filtering programs based on search term
    const filteredPrograms = this.programs.filter(program =>
      program.title.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  
    // Updating totalPrograms based on the filtered programs
    this.totalPrograms = filteredPrograms.length;
  
    // Calculating startIndex and endIndex based on currentPage and programsPerPage
    const startIndex = (this.currentPage - 1) * this.programsPerPage;
    const endIndex = startIndex + this.programsPerPage;
  
    // Slicing the filtered programs based on pagination
    this.filteredPrograms = filteredPrograms.slice(startIndex, endIndex);
  }

  sortByPrice(direction: 'asc' | 'desc') {
    this.filteredPrograms = this.programs.sort((a, b) => {
      if (direction === 'asc') {
        return a.price - b.price;
      } else {
        return b.price - a.price;
      }
    });
    this.applyFilters();
  }
  
  sortByDuration(order: 'asc' | 'desc') {
    this.filteredPrograms.sort((a, b) => {
      if (order === 'asc') {
        return a.duration - b.duration;
      } else {
        return b.duration - a.duration;
      }
    });
  }

  sortByDifficulty(order: 'asc' | 'desc') {
    this.filteredPrograms.sort((a, b) => {
      const difficultyOrder: { [key: string]: number } = { 'Easy': 1, 'Medium': 2, 'Hard': 3 };
      const difficultyA = difficultyOrder[a.difficulty as keyof typeof difficultyOrder];
      const difficultyB = difficultyOrder[b.difficulty as keyof typeof difficultyOrder];
  
      if (order === 'asc') {
        return difficultyA - difficultyB;
      } else {
        return difficultyB - difficultyA;
      }
    });
  }
  

  onProgramSelect(program: Program) {
    console.log(program);
    this.router.navigate(['/program', program.id]);
  }

  fetchNews() {
    this.newsService.getAllNews().subscribe((data: NewsItem[]) => {
      this.newsItems = data;
    });
  }

  activate(): void {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];
      if (token) {
        this.authService.activate(token).subscribe(
          response => {
            console.log('Activation successful:', response);
            // Optionally, you can redirect the user to another page after activation
          },
          error => {
            console.error('Error occurred during activation:', error);
            // Handle error as needed
          }
        );
      }
    });
  }

}
