import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor, HttpResponse
  } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import {catchError, finalize, map} from 'rxjs/operators'
import { NgxSpinner, NgxSpinnerService } from 'ngx-spinner';

@Injectable()
export class SpinnerInterceptor implements HttpInterceptor {
    constructor(
        private ngxSpinner: NgxSpinnerService
    ) { }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        this.ngxSpinner.show();
        return next.handle(request).pipe(
            finalize(()=>this.ngxSpinner.hide())
        );
    }
}