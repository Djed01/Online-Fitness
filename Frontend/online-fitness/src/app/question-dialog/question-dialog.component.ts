import { Component,Inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NgForm } from '@angular/forms';
import { QuestionService } from '../services/question.service';
import { jwtDecode } from 'jwt-decode';
import { Question } from '../models/question.model';
import { UserService } from '../services/user.service';
import { ProgramService } from '../services/program.service';

@Component({
  selector: 'app-question-dialog',
  templateUrl: './question-dialog.component.html',
  styleUrl: './question-dialog.component.css'
})
export class QuestionDialogComponent {
  questionFormValues: any = {};
  question:Question = {} as Question;
  programId:number=1;

  constructor(public dialogRef: MatDialogRef<QuestionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialog: MatDialog,
    private questionService:QuestionService,
    private userService:UserService,
    private programService:ProgramService) {
      this.programId = data.programId;
    }

  closeDialog(): void {
    this.dialogRef.close();
  }

  submitQuestionForm(questionForm: NgForm): void {
    if(questionForm.valid){
      const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          this.question.content = this.questionFormValues.content;
          this.question.date = new Date();
          this.question.userId = userId;
          this.question.programId = this.programId;
          // this.userService.getUserById(userId).subscribe((response)=>{
          //   this.question.user = response;
          // });
          // this.programService.getProgramById(this.programId).subscribe((response)=>{
          //   this.question.program = response;
          // })
      this.questionService.createQuestion(this.question).subscribe((response)=>{
        console.log("Succesfully added a new question!");
        this.closeDialog();
      });
    }
  }
}
  }
}
