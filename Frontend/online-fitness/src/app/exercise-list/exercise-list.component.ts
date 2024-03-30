import { Component, OnInit } from '@angular/core';
import { ExerciseService } from '../services/exercise.service';
import { Observable } from 'rxjs';
import { Exercise } from '../models/exercise.model';


@Component({
  selector: 'app-exercise-list',
  templateUrl: './exercise-list.component.html',
  styleUrls: ['./exercise-list.component.css']
})
export class ExerciseListComponent implements OnInit {
  exercises: any;
  loading: boolean = false;

  constructor(private exerciseService: ExerciseService) { }

  ngOnInit(): void {
    this.loading = true;
    this.exerciseService.getExercises().subscribe({
      next: (data) => {
       // console.log(data);
        this.exercises = data;
      },
  }).add(() => this.loading = false);
}
}
