import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http:HttpClient, private sanitizer: DomSanitizer) { }

  uploadImage(url: any,programId:number){
    const formData = new FormData();
    formData.append('image', url);
    return this.http.post(`http://localhost:8080/api/image/${programId}`, formData);
  }


  downloadImage(id: number): Observable<string> {
    return new Observable(observer => {
      this.http.get(`http://localhost:8080/api/image/${id}`, { responseType: 'blob' }).subscribe(
        (data: Blob) => {
          const objectURL = URL.createObjectURL(data);
          observer.next(objectURL);
          observer.complete();
        },
        error => {
          console.error('Error fetching image: ', error);
          observer.error('Error fetching image');
        }
      );
    });
  }
}
