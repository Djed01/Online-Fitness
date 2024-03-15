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
import { jwtDecode } from 'jwt-decode';
import { ParticipationService } from '../services/participation.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { QuestionDialogComponent } from '../question-dialog/question-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';

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
  participates: boolean = false;
  isLoggedIn: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private programService : ProgramService,
    private commentService : CommentService,
    private userService : UserService,
    private dialog: MatDialog,
    private avatarService: AvatarService,
    private imageService: ImageService,
    private participationService: ParticipationService,
    private sanitizer: DomSanitizer,
    private snackBar: MatSnackBar,
    ){}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          this.isLoggedIn = true;
        }
      }
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
    this.checkParticipation();
  }

  loadComments() {
    this.commentService.getAllCommentsByProgramId(this.programId).subscribe((data: Comment[]) => {
      this.comments = data;
  
      for (const comment of this.comments) {
        this.userService.getUserById(comment.userId).subscribe((user: User) => {
          comment.username = user.username;
          if(user.avatarId){
          this.avatarService.downloadAvatar(user.avatarId).subscribe((url: string) => {
            comment.avatar = url;
          });
        }else{
          comment.avatar = this.image;
        }
        });
      }
    });
  }

  addComment() {
    if(this.newComment.content.trim()){
      const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          this.newComment.userId = userId; 
          this.newComment.programId = this.programId; 
          this.newComment.date = new Date(); 
          this.commentService.addComment(this.newComment).subscribe(
            (response) => {
              console.log('Comment posted successfully:', response);
              this.newComment = {} as Comment;
              this.loadComments();
            },
            (error) => {
              this.snackBar.open("Error occurred while posting comment.", 'Close', {
                duration: 3000,
              });
              console.error('Error occurred while posting comment:', error);
            }
          );
        }
        }
      }
  }

  openPaymentDialog(): void {
    const dialogRef = this.dialog.open(PaymentDialogComponent, {
      data: { programId: this.programId } 
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    }); 
  }

  openQuestionDialog(): void {
    const dialogRef = this.dialog.open(QuestionDialogComponent, {
      data: { programId: this.programId }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    }); 
  }


  checkParticipation() {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken: any = jwtDecode(token);
      const userId = decodedToken.id;
      if (userId) {
        this.participationService.participates(this.programId, userId).subscribe(
          (response) => {
            this.participates = response; 
          },
          (error) => {
            console.error('Error occurred while checking participation:', error);
          }
        );
      }
    }
  }

  sanitizeURL(url: string): SafeResourceUrl {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  openLinkInNewTab() {
    if (this.program && this.program.link) {
      window.open(this.program.link, '_blank');
    }
  }
}
