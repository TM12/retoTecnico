import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InputCurrency } from '../types/inputCurrency.type';
import { Currency } from '../types/currency.type';
import { environment } from '../../environments/environment';
const { host } = environment;

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  headers = new HttpHeaders()
    .append('Content-Type', 'application/json')
    .append('Accept', 'application/json');
  constructor(private http:HttpClient) { }

  getAllCurriencies(){  
    return this.http.get(`${host}/currencies`,{ headers: this.headers });
  }

  convertedAmount(input:InputCurrency){
    return this.http.post(`${host}/converted`,input);
  }

  updateCurrency(currency:Currency){
    return this.http.post(`${host}/converted`,currency);
  }
  
}
