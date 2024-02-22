import { Component, OnInit } from '@angular/core';
import { Program } from '../models/program.model';
import { ProgramService } from '../services/program.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrls: ['./programs.component.css'] 
})
export class ProgramsComponent implements OnInit {
  programs: Program[] = [];
  filteredPrograms: Program[] = [];
  photo: string = 'assets/images/gym.jpg';
  currentPage: number = 1;
  programsPerPage: number = 10;
  totalPrograms: number = 0;
  searchTerm: string = '';

  constructor(private programService: ProgramService, private router: Router) {}

  ngOnInit() {
    this.loadPrograms();
  }

  loadPrograms() {
    this.programService.getAllPrograms().subscribe((data: Program[]) => {
      this.programs = data;
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
  
  
}
