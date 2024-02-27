import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProgramService } from '../services/program.service';
import { Program} from '../models/program.model';
import {Image} from '../models/image.model';
import {ProgramAttribute} from '../models/programAttribute.model';

@Component({
  selector: 'app-create-program',
  templateUrl: './create-program.component.html',
  styleUrl: './create-program.component.css'
})
export class CreateProgramComponent implements OnInit {
  programForm!: FormGroup;
  difficulties: string[] = ['Easy', 'Medium', 'Hard'];
  categories: string[] = ['Category 1', 'Category 2', 'Category 3']; // Mock data for categories
  attributes: ProgramAttribute[] = [{attributeId:1 ,attributeName: 'Attribute 1'}, {attributeId:2 ,attributeName: 'Attribute 2'}, {attributeId:3 ,attributeName: 'Attribute 3'}
,{attributeId:1 ,attributeName: 'Attribute 1'},{attributeId:1 ,attributeName: 'Attribute 1'},]; // Mock data for attributes

  constructor(private fb: FormBuilder /* , private programService: ProgramService */) { }

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

    // Mock data for categories and attributes (commented out service calls)
    /* this.programService.getCategories().subscribe(categories => {
      this.categories = categories;
    });

    this.programService.getAttributes().subscribe(attributes => {
      this.attributes = attributes;
    }); */
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
}
