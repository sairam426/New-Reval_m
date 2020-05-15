import { FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-order-search',
  templateUrl: './order-search.component.html'
})
export class OrderSearchComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  isSaving = false;
  searchForm = this.fb.group({
    ordNumber:[],

  });
  ngOnInit(): void {
    
    
  }
  save(){

  }

  previousState(): void {
    window.history.back();
  }

}
