import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProgramsComponent } from './programs/programs.component';
import { ConcreteProgramComponent } from './concrete-program/concrete-program.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ExerciseListComponent } from './exercise-list/exercise-list.component';

const routes: Routes = [
  { path: '', component: ProgramsComponent },
  { path: 'program/:programId',component:ConcreteProgramComponent},
  { path: 'profile',component:UserProfileComponent},
  { path: 'exercises', component:ExerciseListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
