/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { ActivityType1Component } from './activity-type1.component';

describe('ActivityType1Component', () => {
  let component: ActivityType1Component;
  let fixture: ComponentFixture<ActivityType1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityType1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityType1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
