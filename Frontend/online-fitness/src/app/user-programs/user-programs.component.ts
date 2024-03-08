import { Component } from '@angular/core';
import { ProgramService } from '../services/program.service';
import { Program } from '../models/program.model';
import { jwtDecode } from 'jwt-decode';

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
    const token = localStorage.getItem('token');
    if(token){
      const decodedToken: any = jwtDecode(token);
      const userId = decodedToken.id;
      if (userId) {
    this.programService.getProgramsByUserId(userId).subscribe((data: Program[]) => {
      this.programs = data;
      this.filterPrograms(this.status);
      });
      }
    }
  }

  filterPrograms(status: boolean) {
    this.status = status;
    this.filteredPrograms = this.programs.filter(program => program.status === status);
  }

  deleteProgram(id:number){
    console.log("OP BRISEM ");
    this.programService.deleteProgram(id).subscribe((response)=>{
      console.log(response);
    })
  }
}
