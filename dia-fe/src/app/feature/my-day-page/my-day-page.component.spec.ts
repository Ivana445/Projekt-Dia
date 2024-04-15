import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyDayPageComponent } from './my-day-page.component';

describe('MyDayPageComponent', () => {
  let component: MyDayPageComponent;
  let fixture: ComponentFixture<MyDayPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyDayPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MyDayPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
