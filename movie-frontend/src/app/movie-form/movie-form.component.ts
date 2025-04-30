import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MovieService } from '../movie.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-movie-form',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.css']
})
export class MovieFormComponent {
  movie: any = {};

  constructor(private movieService: MovieService) { }

  onSubmit(): void {
    this.movieService.addMovie(this.movie).subscribe(
      response => {
        console.log('Movie added successfully', response);
        this.movie = {}; // Reset form
      },
      error => console.error('Error adding movie:', error)
    );
  }
}
