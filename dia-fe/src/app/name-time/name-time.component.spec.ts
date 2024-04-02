import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NameTimeComponent } from './name-time.component';

describe('NameTimeComponent', () => {
  let component: NameTimeComponent;
  let fixture: ComponentFixture<NameTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NameTimeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NameTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
