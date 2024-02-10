import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminyemekkonsoluComponent } from './adminyemekkonsolu.component';

describe('AdminyemekkonsoluComponent', () => {
  let component: AdminyemekkonsoluComponent;
  let fixture: ComponentFixture<AdminyemekkonsoluComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminyemekkonsoluComponent]
    });
    fixture = TestBed.createComponent(AdminyemekkonsoluComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
