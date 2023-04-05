import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePostPopupComponent } from './update-post-popup.component';

describe('UpdatePostPopupComponent', () => {
  let component: UpdatePostPopupComponent;
  let fixture: ComponentFixture<UpdatePostPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatePostPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatePostPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
