import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWeightDialogComponent } from './add-weight-dialog.component';

describe('AddWeightDialogComponent', () => {
  let component: AddWeightDialogComponent;
  let fixture: ComponentFixture<AddWeightDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddWeightDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddWeightDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
