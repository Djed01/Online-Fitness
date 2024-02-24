import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewsItem } from '../models/newsItem.model';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  getAllNews() {
    return this.http.get<NewsItem[]>(`http://localhost:8080/api/news`);
  }
}
