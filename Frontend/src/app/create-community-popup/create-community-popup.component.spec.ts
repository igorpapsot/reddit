import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCommunityPopupComponent } from './create-community-popup.component';

describe('CreateCommunityPopupComponent', () => {
  let component: CreateCommunityPopupComponent;
  let fixture: ComponentFixture<CreateCommunityPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCommunityPopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCommunityPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
