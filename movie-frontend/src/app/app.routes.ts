import { Routes } from '@angular/router';
import { MovieListComponent } from './movie-list/movie-list.component';
import { MovieFormComponent } from './movie-form/movie-form.component';

export const routes: Routes = [
  { path: 'movies', component: MovieListComponent },
  { path: 'add', component: MovieFormComponent },
  { path: '', redirectTo: '/movies', pathMatch: 'full' }
];
