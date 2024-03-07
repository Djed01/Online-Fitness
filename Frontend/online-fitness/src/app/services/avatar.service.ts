import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';


@Injectable({
  providedIn: 'root'
})
export class AvatarService {

  constructor(private http:HttpClient, private sanitizer: DomSanitizer) { }

  uploadAvatar(url: any,userId:number){
    const formData = new FormData();
    formData.append('avatar', url);
    return this.http.post(`http://localhost:8080/api/avatar/${userId}`, formData);
  }

  updateAvatar(url: any,userId:number){
    const formData = new FormData();
    formData.append('avatar', url);
    return this.http.post(`http://localhost:8080/api/avatar/update/${userId}`, formData);
  }

  downloadAvatar(id: number): Observable<string> {
    return new Observable(observer => {
      this.http.get(`http://localhost:8080/api/avatar/${id}`, { responseType: 'blob' }).subscribe(
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
