import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CurrencyComponent } from './components/currency/currency.component';

const routes: Routes = [
  {path:'currency',component:CurrencyComponent},
  {path:'', pathMatch: 'full', redirectTo:'currency'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
