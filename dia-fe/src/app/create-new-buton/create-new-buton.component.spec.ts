import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewButonComponent } from './create-new-buton.component';

describe('CreateNewButonComponent', () => {
  let component: CreateNewButonComponent;
  let fixture: ComponentFixture<CreateNewButonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateNewButonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateNewButonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
