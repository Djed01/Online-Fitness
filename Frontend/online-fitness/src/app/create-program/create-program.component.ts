import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProgramService } from '../services/program.service';
import { Program} from '../models/program.model';
import {Image} from '../models/image.model';
import { CategoryAttribute } from '../models/attribute.model';
import {CategoryService} from '../services/category.service';
import { Category } from '../models/category.model';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-create-program',
  templateUrl: './create-program.component.html',
  styleUrl: './create-program.component.css'
})
export class CreateProgramComponent implements OnInit {
  programForm!: FormGroup;
  difficulties: string[] = ['Easy', 'Medium', 'Hard'];
  categories: Category[] = [];
  attributes: CategoryAttribute[] = []; 
  selectedCategory!: Category;
  selectedFiles: File[] = [];

  constructor(private fb: FormBuilder,
    private categoryService:CategoryService,
    private programService:ProgramService,
    private imageService:ImageService) { }

  ngOnInit(): void {
    this.programForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      images: [[]],
      duration: ['', Validators.required],
      difficulty: ['', Validators.required],
      location: ['', Validators.required],
      instructorName: ['', Validators.required],
      instructorSurname: ['', Validators.required],
      instructorContact: ['', Validators.required],
      creationDate: [new Date()],
      categoryName: ['', Validators.required],
      userId: ['', Validators.required],
      attributes: [[]]
    });

    this.categoryService.getAllCategorys().subscribe(data =>{
      this.categories = data;
    })
  }

  onSubmit() {
    this.setCategoryAttributes();
    console.log(this.attributes);
    const formData = this.programForm.value;
    const program: Program = {
      ...formData
    };

    program.userId = 1; // KASNIJE DODATI ID KOSRINIKA
    program.categoryId = this.selectedCategory.id;

    console.log('ATRIBUTI PRIJE SUBMITA:', this.attributes.map(attribute => attribute.name).join(','));
    program.attributes = [];
    this.attributes.forEach(attribute => {
      const attributeControl = this.programForm.get(`attribute${attribute.id}`)!;
      // Check if the checkbox is checked
      if (attributeControl.value) {
        // If selected, add the attribute to the program's attributes array
        program.attributes.push(attribute);
      }
    });


    console.log('Program data:', program);
    this.programService.createProgram(program).subscribe(result => {
      this.uploadImages(result.id);
      console.log('Program created successfully:', result);
    }); 
  }


  setCategoryAttributes() {
    const selectedCategoryName = this.programForm.get('categoryName')?.value;
    this.selectedCategory = this.categories.find(category => category.name === selectedCategoryName)!;
    if (this.selectedCategory) {
      this.attributes = this.selectedCategory.categoryAttributes;
           // Generate form controls for attributes
      this.attributes.forEach(attribute => {
        this.programForm.addControl('attribute' + attribute.id, this.fb.control(false));
      });
    } else {
      this.attributes = [];
    }
  }
  

  onFileSelected(event: any): void {
    this.selectedFiles = event.target.files;
  }

   uploadImages(programId:number): void {
    if (!this.selectedFiles) return;
    for(var file of this.selectedFiles){
    this.imageService.uploadImage(file,programId).subscribe((data: any) => {
      console.log(data);
    });
    }
  }
  
}
