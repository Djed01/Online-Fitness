import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProgramsComponent } from './programs/programs.component';
import { ConcreteProgramComponent } from './concrete-program/concrete-program.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ExerciseListComponent } from './exercise-list/exercise-list.component';
import { ParticipationsComponent } from './participations/participations.component';
import { CreateProgramComponent } from './create-program/create-program.component';
import { UserProgramsComponent } from './user-programs/user-programs.component';
import { ChatComponent } from './chat/chat.component';

const routes: Routes = [
  { path: '', component: ProgramsComponent },
  { path: 'program/:programId',component:ConcreteProgramComponent},
  { path: 'profile',component:UserProfileComponent},
  { path: 'exercises', component:ExerciseListComponent},
  { path: 'participations', component:ParticipationsComponent},
  { path: 'create', component:CreateProgramComponent },
  { path: 'myprograms', component:UserProgramsComponent},
  { path: 'chat', component:ChatComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
