import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProgramsComponent } from './programs/programs.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { ConcreteProgramComponent } from './concrete-program/concrete-program.component';
import { ImageCarouselComponent } from './image-carousel/image-carousel.component';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { PaymentDialogComponent } from './payment-dialog/payment-dialog.component';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { RegisterDialogComponent } from './register-dialog/register-dialog.component';
import { ActivationDialogComponent } from './activation-dialog/activation-dialog.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { EditProfileDialogComponent } from './edit-profile-dialog/edit-profile-dialog.component';
import { ChangePasswordDialogComponent } from './change-password-dialog/change-password-dialog.component';
import { ExerciseListComponent } from './exercise-list/exercise-list.component';
import { MatListModule } from '@angular/material/list';
import { ParticipationsComponent } from './participations/participations.component';
import { CreateProgramComponent } from './create-program/create-program.component';
import { MatSelectModule } from '@angular/material/select';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { UserProgramsComponent } from './user-programs/user-programs.component';
import { QuestionDialogComponent } from './question-dialog/question-dialog.component';
import { ChatComponent } from './chat/chat.component';
import { UserSelectionDialogComponent } from './user-selection-dialog/user-selection-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UserActivityComponent } from './user-activity/user-activity.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { AddActivityDialogComponent } from './add-activity-dialog/add-activity-dialog.component';
import { AddWeightDialogComponent } from './add-weight-dialog/add-weight-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    ProgramsComponent,
    ConcreteProgramComponent,
    ImageCarouselComponent,
    PaymentDialogComponent,
    LoginDialogComponent,
    RegisterDialogComponent,
    ActivationDialogComponent,
    UserProfileComponent,
    EditProfileDialogComponent,
    ChangePasswordDialogComponent,
    ExerciseListComponent,
    ParticipationsComponent,
    CreateProgramComponent,
    UserProgramsComponent,
    QuestionDialogComponent,
    ChatComponent,
    UserSelectionDialogComponent,
    UserActivityComponent,
    AddActivityDialogComponent,
    AddWeightDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatToolbarModule,
    HttpClientModule,
    MatIconModule,
    FormsModule,
    MatFormFieldModule,
    FontAwesomeModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatRadioModule,
    ReactiveFormsModule,
    MatListModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDialogModule,
    NgxChartsModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
