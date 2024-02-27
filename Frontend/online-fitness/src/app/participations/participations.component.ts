import { Component } from '@angular/core';
import { ProgramService } from '../services/program.service';
import { Program } from '../models/program.model';

@Component({
  selector: 'app-participations',
  templateUrl: './participations.component.html',
  styleUrl: './participations.component.css'
})
export class ParticipationsComponent {
  programs: Program[] = [];

  constructor(private programService: ProgramService){}

  ngOnInit() {
    this.loadPrograms();
  }

  loadPrograms() {
    this.programService.getAllPrograms().subscribe((data: Program[]) => {
      this.programs = data;
    });
  }

}
