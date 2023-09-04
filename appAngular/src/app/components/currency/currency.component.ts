import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Regex } from 'src/app/constants/Regex.contants';
import { ApiService } from 'src/app/service/api.service';
import { Currency } from 'src/app/types/currency.type';
import { InputCurrency } from 'src/app/types/inputCurrency.type';
import { OutputCurrency } from 'src/app/types/outputCurrency.type';

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.sass'],
})
export class CurrencyComponent implements OnInit {
  curriencies: Currency[];
  output: OutputCurrency;
  input: InputCurrency;
  formInput: FormGroup;
  exchangeRageOrigin:any=0;
  exchangeRangeDestination:any=0;
  constructor(private fb: FormBuilder, private service:ApiService) {
    this.formInput = this.createForm();
  }

  ngOnInit(): void {
    this.getAllCurriencies();
  }
  
  changeValueForm(){
    if(this.formInput.get("amountToConvert")?.value>0){
        this.converted();
    }else{
        this.input = {}
        this.output = {}
    }
  }

  createForm() {
    return this.fb.group({
      amountToConvert: [
        null,
        [Validators.required, Validators.pattern(Regex.soloNumeros)],
      ],
      originCurrency: [null, [Validators.required]],
      destinationCurrency: [null, [Validators.required]],
    });
  }

  converted() {
    if (this.formInput.valid) {
      this.input = this.formInput.value;
      this.service.convertedAmount(this.input).subscribe(
        (resp:any)=>{
            this.output = resp;
            let valueOrigin:number = this.getExchangeRate(this.input.originCurrency)?.valueExchange || 0;
            let valueDestination:number = this.getExchangeRate(this.input.destinationCurrency)?.valueExchange || 0;
            this.exchangeRageOrigin = (valueOrigin/valueDestination);
            this.exchangeRangeDestination = (valueDestination/valueOrigin);
            console.log("output => ", resp);
        },
        (error)=>{
            console.log("Error => ", error);
        }
      )
    }else{
        console.log("Existen campos obligatorios sin enviar");  
    }
  }

  getAllCurriencies(){
    this.service.getAllCurriencies().subscribe(
        (resp:any)=>{
            this.curriencies = resp;
        },
        (error)=>{
            console.log("Error => ", error)
        }
    )
  }

  getExchangeRate(abbreviation:any){
    return this.curriencies.find(el=>el.abbreviation==abbreviation);
  }
  
}
