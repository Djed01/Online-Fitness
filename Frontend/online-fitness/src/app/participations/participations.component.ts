import { Component } from '@angular/core';
import { ProgramService } from '../services/program.service';
import { Program } from '../models/program.model';
import { ParticipationService } from '../services/participation.service';
import { jwtDecode } from 'jwt-decode';
import { Participation } from '../models/participation.model';

@Component({
  selector: 'app-participations',
  templateUrl: './participations.component.html',
  styleUrl: './participations.component.css'
})
export class ParticipationsComponent {
  programs: Program[] = [];

  constructor(private programService: ProgramService
    ,private participationService:ParticipationService){}

  ngOnInit() {
    this.loadPrograms();
  }

  loadPrograms() {
    const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
    this.participationService.getAllByUserId(userId).subscribe((data: Participation[]) => {
        for(var participation of data){
          this.programService.getProgramById(participation.programId).subscribe((program:Program)=>{
            this.programs.push(program);
          })
        }
    });
    }
  }
}

}
