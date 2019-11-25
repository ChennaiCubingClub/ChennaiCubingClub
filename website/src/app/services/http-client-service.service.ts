import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HttpClientServiceService {

    constructor(private httpClient: HttpClient) { }

    post(url, params) {
      return this.httpClient.post(url, params, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      }).pipe(
        catchError(this.handleError('API error'))
      )
    }
  
    postAuth(url, params, token) {
          var args = new HttpParams();
          for (var key in params) {
              args = args.append(key, params[key])
          }
          return this.httpClient.post(url, args, {
          headers: new HttpHeaders({
              'Content-Type': 'application/x-www-form-urlencoded',
              'Authorization': token
          })
          }).pipe(
          catchError(this.handleError('API error'))
          )
    }
  
    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T> (operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
  
        // TODO: send the error to remote logging infrastructure
        console.error(error); // log to console instead
  
        // TODO: better job of transforming error for user consumption
        this.log(`${operation} failed: ${error.message}`);
  
        // Let the app keep running by returning an empty result.
        return of(result as T);
      };
    }
  
    /** Log a HeroService message with the MessageService */
    private log(message: string) {
      console.log(`HeroService: ${message}`)
    }
}
