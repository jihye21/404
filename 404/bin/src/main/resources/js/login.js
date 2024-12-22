@import "compass";
@import url(https://fonts.googleapis.com/css?family=Dancing+Script|Roboto);
*, *:after, *:before{
  @include box-sizing(border-box);
}

$primary : #FF4081;

body{
  background: $primary/ 1.25;
  text-align: center;
  font-family: 'Roboto', sans-serif;
}
.panda{
  position: relative;
  width: 200px;
  margin: 50px auto;
  
}

.face{
  width: 200px;
  height: 200px;
  background: #fff;
  @include border-radius(100%);
  margin: 50px auto;
  @include box-shadow(0 10px 15px rgba(0,0,0,.15));
  z-index: 50;
  position: relative;
  
}
.ear{
  position: absolute;
  width: 80px;
  height: 80px;
  background: #000;
  z-index: 5;
  border:10px solid #fff;
  left: -15px;
  top: -15px;
  @include border-radius(100%);
  &:after{
    content: '';
    @extend .ear;
    left: 125px;
  }
}
.eye-shade{
  background: #000;
  width: 50px;
  height: 80px;
  margin: 10px;
  position: absolute;
  top: 35px;
  left: 25px;
  @include rotate(220deg);
  @include border-radius(25px / 20px 30px 35px 40px);
  
   &.rgt{
        @include rotate(140deg);
        left: 105px;
    }
}

  .eye-white{
    position: absolute;
    width: 30px;
    height: 30px;
    @include border-radius(100%);
    background: #fff;
    z-index: 500;
    left: 40px;
    top: 80px;
    overflow: hidden;
    
     &.rgt{
        right: 40px;
        left: auto;
    }
  }
  .eye-ball{
        position: absolute;
        width: 0px;
        height: 0px;
        left: 20px;
        top: 20px;
        max-width: 10px;
        max-height: 10px;
      @include transition(.1s);
      

          &:after{
              content: '';
              background: #000;
              position: absolute;
              @include border-radius(100%);
              right: 0;
              bottom: 0px;
              width: 20px;
              height: 20px;
          }
      
    }
  
   
  

.nose{
  position: absolute;
  height: 20px;
  width: 35px;
  bottom: 40px;
  left: 0;
  right: 0;
  margin: auto;
  @include border-radius(50px  20px/ 30px 15px);
   @include rotate(15deg);
  background: #000;
}
.body{
  background: #fff;
  position: absolute;
  top: 200px;
  left: -20px;
  @include border-radius(100px 100px 100px 100px / 126px 126px 96px 96px);
  width: 250px; 
  height: 282px;
  @include box-shadow(0 5px 10px rgba(0,0,0,.3));
}
.hand{
  width: 40px;
  height: 30px;
  @include border-radius(50px);
  @include box-shadow(0 2px 3px rgba(0,0,0,.15));
  background: #000;
  margin: 5px;
  position: absolute;
  top: 70px;
  left: -25px;
  &:after, &:before{
    content: '';
    @extend .hand;
    left: -5px;
    top: 11px;
  }
  &:before{
    top: 26px;
  }
  
  &.rgt{
    left: auto;
    right: -25px;
    &:after, &:before{
      left: auto;
      right: -5px;
    }
  }
}
.foot{
  top: 360px;
  left: -80px;
  position: absolute;
  background: #000;
  z-index: 1400;
  @include box-shadow(0 5px 5px rgba(0,0,0,.2));
@include  border-radius(40px 40px 39px 40px / 26px 26px 63px 63px);
  width: 82px; 
  height: 120px;
  &:after{
    content: '';
    width: 55px;
    height: 65px;
    background: #222;
    @include  border-radius(100%);
    position: absolute;
    bottom: 10px;
    left: 0;
    right: 0;
    margin: auto;
  }
  
  
  .finger{
    position: absolute;
    width: 25px;
    height: 35px;
    background: #222;
    @include  border-radius(100%);
    top: 10px;
    right: 5px;
    &:after, &:before{
      content: '';
      @extend .finger;
      right: 30px;
      width: 20px;
      top: 0;
    }
    &:before{
      right: 55px;
      top: 5px;
    }
  }
  
  
  &.rgt{
    left: auto;
    right: -80px;
    .finger{
      left: 5px;
      right: auto;
      &:after{
        left: 30px;
        right: auto;
        
      }
       &:before{
          left: 55px;
          right: auto;
        }
    }
  }
  
  
}


form{
  display: none;
  max-width: 400px;
  padding: 20px 40px;
  background: #fff;
  height: 300px;
  margin: auto;
  display: block;
  @include box-shadow(0 10px 15px rgba(0,0,0,.15));
  @include transition(.3s);
  position: relative;
  @include translateY(-100px);
  z-index: 500;
  border:1px solid #eee;
  
  &.up{
      @include translateY(-180px);
  }
}

h1{
  color: $primary;
  font-family: 'Dancing Script', cursive;
}
.btn{
  background: #fff;
  padding: 5px;
  width: 150px;
  height: 35px;
  border:1px solid $primary;
  margin-top: 25px;
  cursor: pointer;
  @include transition(.3s);
  @include box-shadow(0 50px $primary inset);
  color: #fff;
  
    &:hover{
        @include box-shadow(0 0 $primary inset);
        color: $primary;
    }
    &:focus{
      outline: none;
    }
}
.form-group{
 
  position: relative;
  font-size: 15px;
  color: #666;
  &+&{
    margin-top: 30px;
  }
  
  .form-label{
    position: absolute;
    z-index: 1;
    left: 0;
    top: 5px;
    @include transition(.3s);
    
  }
  
  .form-control{
    width: 100%;
    position: relative;
    z-index: 3;
    height: 35px;
    background: none;
    border:none;
    padding: 5px 0;
    @include transition(.3s);
    border-bottom: 1px solid #777;
    color: #555;
    &:invalid{outline: none;}
    
    &:focus , &:valid{
      outline: none;

      @include box-shadow(0 1px $primary);
      border-color:$primary;
      + .form-label{
        font-size: 12px;
        color: $primary;
        @include translateY(-15px);
      }
    }
  }
  
}
.alert{
  position: absolute;
  color: #f00;
  font-size: 16px;
  right: -180px;
  top: -300px;
  z-index: 200;
  padding: 30px 25px;
  background: #fff;
  @include box-shadow(0 3px 5px rgba(0,0,0,.2));
   @include  border-radius(50%);
  opacity: 0;
  @include scale(0);
  @include transition(.4s .6s linear);
  &:after, &:before{
    content: '';
    position: absolute;
    width: 25px;
    height: 25px;
    background: #fff;
    left: -19px;
    bottom: -8px;
    @include box-shadow(0 2px 5px rgba(0,0,0,.2));
    @include  border-radius(50%);
  }
  &:before{
     width: 15px;
    height: 15px;
    left: -35px;
    bottom: -25px;
  }
}

.wrong-entry {
 @include animation( wrong-log 0.3s);
 
  .alert{
    opacity: 1;
    @include scale(1);
  }
}

@include keyframes (eye-blink) {
  to { height: 30px;}
}
@include keyframes (wrong-log) {
  0%, 100% { left: 0px;}
  20% , 60%{left: 20px;}
  40% , 80%{left: -20px;}
}