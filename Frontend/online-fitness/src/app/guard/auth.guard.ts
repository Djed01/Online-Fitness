import { CanActivateFn } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

export const authGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('token');
      if(token){
        const decodedToken: any = jwtDecode(token);
        const userId = decodedToken.id;
        if (userId) {
          return true;
        }else{
          return false
        }
      }
      return false;
};
