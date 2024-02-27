import { Component } from '@angular/core';
import { ProgramService } from '../services/program.service';
import { Program } from '../models/program.model';

@Component({
  selector: 'app-user-programs',
  templateUrl: './user-programs.component.html',
  styleUrl: './user-programs.component.css'
})
export class UserProgramsComponent {
  programs: Program[] = [];
  filteredPrograms: Program[] = [];
  status: boolean = true; 

  constructor(private programService: ProgramService){}

  ngOnInit() {
    this.loadPrograms();
  }

  loadPrograms() {
    this.programService.getAllPrograms().subscribe((data: Program[]) => {
      this.programs = data;
      this.filterPrograms(this.status);
    });
  }

  filterPrograms(status: boolean) {
    this.status = status;
    this.filteredPrograms = this.programs.filter(program => program.status === status);
  }
}
