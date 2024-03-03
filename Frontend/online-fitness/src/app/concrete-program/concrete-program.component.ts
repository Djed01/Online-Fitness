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
import { ImageService } from '../services/image.service';


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
  images: string[] = [];
  photo:string = 'assets/images/gym.jpg';
  image:string = "https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg";

  constructor(
    private route: ActivatedRoute,
    private programService : ProgramService,
    private commentService : CommentService,
    private userService : UserService,
    private dialog: MatDialog,
    private avatarService: AvatarService,
    private imageService: ImageService,
    ){}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const programId = +params['programId']; // '+' converts the string 'topicId' to a number
      if (programId) {
        this.programId = programId;
        this.programService.getProgramById(this.programId).subscribe((data: Program) => {
          if(data){
          this.program = data;
          if (this.program.images && this.program.images.length > 0) {
            for(var image of this.program.images){
            this.imageService.downloadImage(image.id).subscribe((url: string) => {
              console.log(url);
              this.images.push(url);
            },
              error => {
                console.error('Error occurred during fetching images:', error);
                // Handle error as needed
              }
            );
            }
          } else {
            this.images.push(this.photo) // Set a default photo if no image is available
          }
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
          this.avatarService.downloadAvatar(user.avatarId).subscribe((url: string) => {
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
