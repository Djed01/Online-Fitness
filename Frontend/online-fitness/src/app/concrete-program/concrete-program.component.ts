import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProgramService } from '../services/program.service';
import { Program } from '../models/program.model';
import { Comment } from '../models/comment.model';
import { CommentService } from '../services/comment.service';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';
import { MatDialog } from '@angular/material/dialog';
import { PaymentDialogComponent } from '../payment-dialog/payment-dialog.component';
import { AvatarService } from '../services/avatar.service';


@Component({
  selector: 'app-concrete-program',
  templateUrl: './concrete-program.component.html',
  styleUrl: './concrete-program.component.css'
})
export class ConcreteProgramComponent implements OnInit {
  @Input() programId: number = 0;
  program: Program = {} as Program;
  comments: Comment[] = [];
  newComment: Comment = {} as Comment;
  image:string = "https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg";

  constructor(
    private route: ActivatedRoute,
    private programService : ProgramService,
    private commentService : CommentService,
    private userService : UserService,
    private dialog: MatDialog,
    private avatarService: AvatarService,
    ){}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const programId = +params['programId']; // '+' converts the string 'topicId' to a number
      if (programId) {
        this.programId = programId;
        this.programService.getProgramById(this.programId).subscribe((data: Program) => {
          if(data){
          this.program = data;
          console.log(data);
          }
        });
      }
    });
    this.loadComments();
  }

  loadComments() {
    this.commentService.getAllCommentsByProgramId(this.programId).subscribe((data: Comment[]) => {
      this.comments = data;
  
      for (const comment of this.comments) {
        this.userService.getUserById(comment.userId).subscribe((user: User) => {
          comment.username = user.username;
          this.avatarService.downloadAvatar(comment.userId).subscribe((url: string) => {
            comment.avatar = url;
          });
        });
      }
    });
  }

  addComment() {
    // Assuming the new comment object is filled elsewhere in the code
    // Push the new comment to the comments array
    this.newComment.date = new Date(); // Set current date for the new comment
    this.comments.push(this.newComment);
    // Clear the new comment object for the next comment
    this.newComment = {} as Comment;
  }

  openPaymentDialog(): void {
    const dialogRef = this.dialog.open(PaymentDialogComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    }); 
  }
}
