package com.secure.buckledmonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.KeyEvent;

public class SeatBelts {
    public static final String SEATBELTSTATES = "seatBeltsStates";
    public enum SeatBelt{

        //VMCERUIOPQ
        UNKNOWN(0),
        SEATBELT1(KeyEvent.KEYCODE_V),
        SEATBELT2(KeyEvent.KEYCODE_M),
        SEATBELT3(KeyEvent.KEYCODE_C),
        SEATBELT4(KeyEvent.KEYCODE_E),
        SEATBELT5(KeyEvent.KEYCODE_R),
        SEATBELT6(KeyEvent.KEYCODE_U),
        SEATBELT7(KeyEvent.KEYCODE_I),
        SEATBELT8(KeyEvent.KEYCODE_O),
        SEATBELT9(KeyEvent.KEYCODE_P),
        SEATBELT10(KeyEvent.KEYCODE_Q);

        public final int seatID;
        SeatBelt(int seatID) {
            this.seatID = seatID;
        }

        public static SeatBelt findSeatBelt(int id){
            SeatBelt[] seats = values();
            for (SeatBelt seat : seats ){
                if(seat.seatID == id){
                    return seat;
                }
            }
            return UNKNOWN;
        }
    }

    public enum SeatBeltState{
        UNKNOWN(-1),
        OFF(0),
        ON(1);
        public final int intValue;
        SeatBeltState(int intValue) {
            this.intValue = intValue;
        }
    }

}
