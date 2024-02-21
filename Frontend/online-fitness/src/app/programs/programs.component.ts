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
  photo: string = 'assets/images/gym.jpg';

  constructor(private programService: ProgramService, private router: Router) {}

  ngOnInit() {
    this.programService.getAllPrograms().subscribe((data) => {
      this.programs = data;
    });
  }
}
