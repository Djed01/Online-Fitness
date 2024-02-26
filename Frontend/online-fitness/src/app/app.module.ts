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
    MatListModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
