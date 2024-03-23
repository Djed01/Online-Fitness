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
import { UserActivityComponent } from './user-activity/user-activity.component';
import { SubscriptionComponent } from './subscription/subscription.component';
import { authGuard } from './guard/auth.guard';

const routes: Routes = [
  { path: '', component: ProgramsComponent },
  { path: 'program/:programId',component:ConcreteProgramComponent},
  { path: 'profile',component:UserProfileComponent, canActivate:[authGuard]},
  { path: 'exercises', component:ExerciseListComponent,canActivate:[authGuard]},
  { path: 'participations', component:ParticipationsComponent, canActivate:[authGuard]},
  { path: 'create', component:CreateProgramComponent, canActivate:[authGuard] },
  { path: 'myprograms', component:UserProgramsComponent, canActivate:[authGuard]},
  { path: 'chat', component:ChatComponent, canActivate:[authGuard]},
  { path: 'activities', component:UserActivityComponent, canActivate:[authGuard]},
  { path: 'subscription', component:SubscriptionComponent, canActivate:[authGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
