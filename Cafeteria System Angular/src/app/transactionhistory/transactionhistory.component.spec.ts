import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionhistoryComponent } from './transactionhistory.component';

describe('TransactionhistoryComponent', () => {
  let component: TransactionhistoryComponent;
  let fixture: ComponentFixture<TransactionhistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TransactionhistoryComponent]
    });
    fixture = TestBed.createComponent(TransactionhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
