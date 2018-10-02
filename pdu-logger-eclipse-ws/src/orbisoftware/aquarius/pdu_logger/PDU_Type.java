/*
 *  Aquarius DIS PDU Suite
 *
 *  Copyright (C) 2011 Harlan Murphy
 *  Orbis Software - orbisoftware@gmail.com
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package orbisoftware.aquarius.pdu_logger;

// Note that the uN messages are ordinal placeholders representing 
// undefined messages.

public enum PDU_Type {

   Other_Log_All_PDU_Types(0), 
   Entity_State(1), 
   Fire(2), 
   Detonation(3), 
   Collision(4), 
   Service_Request(5), 
   Resupply_Offer(6), 
   Resupply_Received(7), 
   Resupply_Cancel(8), 
   Repair_Complete(9), 
   Repair_Response(10), 
   Create_Entity(11), 
   Remove_Entity(12), 
   Start_Resume(13), 
   Stop_Freeze(14), 
   Acknowledge(15), 
   Action_Request(16), 
   Action_Response(17), 
   Data_Query(18), 
   Set_Data(19), 
   Data(20), 
   Event_Report(21), 
   Comment(22), 
   Electromagnetic_Emission(23), 
   Designator(24), 
   Transmitter(25), 
   Signal(26), 
   Receiver(27), 
   IFF_ATC_NAVAIDS(28), 
   Underwater_Acoustic(29), 
   Supplemental_Emission_Entity_State(30), 
   Intercom_Signal(31), 
   Intercom_Control(32), 
   Aggregate_State(33), 
   Is_Group_Of(34), 
   Transfer_Control(35), 
   Is_Part_Of(36), 
   Minefield_State(37), 
   Minefield_Query(38), 
   Minefield_Data(39), 
   Minefield_Response_NAK(40), 
   Environmental_Process(41), 
   Gridded_Data(42), 
   Point_Object_State(43), 
   Linear_Object_State(44), 
   Areal_Object_State(45), 
   TSPI(46), 
   Appearance(47), 
   Articulated_Parts(48), 
   Live_Entity_Fire(49), 
   Live_Entity_Detonation(50), 
   Create_Entity_R(51), 
   Remove_Entity_R(52), 
   Start_Resume_R(53), 
   Stop_Freeze_R(54), 
   Acknowledge_R(55), 
   Action_Request_R(56), 
   Action_Response_R(57), 
   Data_Query_R(58), 
   Set_Data_R(59), 
   Data_R(60), 
   Event_Report_R(61), 
   Comment_R(62), 
   Record_R(63), 
   Set_Record_R(64), 
   Record_Query_R(65), 
   Collision_Elastic(66), 
   Entity_State_Update(67),
   u68, u69, u70, u71, u72, u73, u74, u75, u76, u77, u78, u79, u80, u81, u82,
   u83, u84, u85, u86, u87, u88, u89, u90, u91, u92, u93, u94, u95, u96, u97,
   u98, u99, u100, u101, u102, u103, u104, u105, u106, u107, u108, u109, u110,
   u111, u112, u113, u114, u115, u116, u117, u118, u119, u120, u121, u122,
   u123, u124, u125, u126, u127, u128,
   Announce_Object(129), 
   Delete_Object(130), 
   Describe_Application(131), 
   Describe_Event(132), 
   Describe_Object(133), 
   Request_Event(134), 
   Request_Object(135);

   private int value;

   PDU_Type() {
      this.value = this.ordinal();
   }

   PDU_Type(int value) {
      this.value = this.ordinal();
   }

   public int value() {
      return value;
   }
}
