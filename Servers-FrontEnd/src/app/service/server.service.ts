import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {CustomResponse} from "../interface/custom-response";
import {Server} from "../interface/server";
import {Status} from "../enum/status.enum";
import {stat} from "fs";

@Injectable({
  providedIn: 'root'
})
export class ServerService {
  private readonly apiUrl = 'any';

  constructor(private http: HttpClient) { }

  /**getServers(): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`http://localhost:8080/server/list`)
  }**/

  servers$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/server/list`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  save$ = (server: Server) => <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.apiUrl}/server/save`, server)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  ping$ = (ipAddress: string) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/server/ping/${ipAddress}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  delete$ = (serverId: number) => <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.apiUrl}/server/delete/${serverId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  filter$ = (status: Status, response: CustomResponse) => <Observable<CustomResponse>>
  new Observable<CustomResponse>(
    suscriber => {
      console.log(response);
      suscriber.next(
        status === Status.All ?
          { ...response, message: `Servers filetered by ${status} status`} :
          { ...response, message: response.data.servers
            .filter((server: { status: Status; }) => server.status === status).length > 0 ?
              `Servers filetered by ${status === Status.SERVER_UP ? 'SERVER UP' : 'SERVER DOWN'} status` :
              `No servers of ${status} found`,
            data: { servers: response.data.servers
                    .filter((server: { status: Status; }) => server.status === status) }
          }
      );
      suscriber.complete();
    }
  )


  private handleError(error: HttpErrorResponse  ): Observable<never> {
    console.log(error)
    return throwError(`Àn error occurred - Error code : ${error.status}`)
  }
}
