/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { ActivityType3Component } from './activity-type3.component';

describe('ActivityType3Component', () => {
  let component: ActivityType3Component;
  let fixture: ComponentFixture<ActivityType3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityType3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityType3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
