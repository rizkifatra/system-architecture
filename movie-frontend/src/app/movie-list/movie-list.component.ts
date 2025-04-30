import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MovieService } from '../movie.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {
  movies: any[] = [];
  searchTerm: string = '';
  searchType: string = 'title';

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.movieService.getMovies().subscribe(
      data => this.movies = data,
      error => console.error('Error fetching movies:', error)
    );
  }

  searchMovies(): void {
    if (this.searchTerm) {
      this.movieService.searchMovies(this.searchTerm, this.searchType).subscribe(
        data => this.movies = data,
        error => console.error('Error searching movies:', error)
      );
    } else {
      this.loadMovies();
    }
  }
}
