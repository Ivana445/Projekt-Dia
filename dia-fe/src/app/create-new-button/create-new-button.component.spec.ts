import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewButtonComponent } from './create-new-button.component';

describe('CreateNewButtonComponent', () => {
  let component: CreateNewButtonComponent;
  let fixture: ComponentFixture<CreateNewButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateNewButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateNewButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
