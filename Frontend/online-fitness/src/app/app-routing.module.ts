import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProgramsComponent } from './programs/programs.component';
import { ConcreteProgramComponent } from './concrete-program/concrete-program.component';

const routes: Routes = [
  { path: '', component: ProgramsComponent },
  { path: 'program/:programId',component:ConcreteProgramComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
