import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProgramService } from '../services/program.service';
import { Program} from '../models/program.model';
import {Image} from '../models/image.model';
import { CategoryAttribute } from '../models/attribute.model';
import {CategoryService} from '../services/category.service';
import { Category } from '../models/category.model';

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

  constructor(private fb: FormBuilder,private categoryService:CategoryService) { }

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

    /* this.programService.getCategories().subscribe(categories => {
      this.categories = categories;
    }); */
    this.categoryService.getAllCategorys().subscribe(data =>{
      this.categories = data;
    })
  }

  onSubmit() {
    const formData = this.programForm.value;
    const program: Program = {
      id: 0, // You may want to handle this differently based on your backend logic
      ...formData
    };
    // Mock call to log the program data
    console.log('Program data:', program);
    /* this.programService.createProgram(program).subscribe(result => {
      console.log('Program created successfully:', result);
    }); */
  }

  onImageChange(event: any) {
    // Handle image upload here if needed
  }


  setCategoryAttributes() {
    const selectedCategoryName = this.programForm.get('categoryName')?.value;
    const selectedCategory = this.categories.find(category => category.name === selectedCategoryName);
    if (selectedCategory) {
      this.attributes = selectedCategory.categoryAttributes;
    } else {
      this.attributes = [];
    }
  }
}
