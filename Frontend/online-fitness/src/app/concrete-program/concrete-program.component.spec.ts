import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConcreteProgramComponent } from './concrete-program.component';

describe('ConcreteProgramComponent', () => {
  let component: ConcreteProgramComponent;
  let fixture: ComponentFixture<ConcreteProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConcreteProgramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConcreteProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
