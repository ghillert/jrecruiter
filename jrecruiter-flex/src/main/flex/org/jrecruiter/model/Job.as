package org.jrecruiter.model {
[Bindable]
	[RemoteClass(alias="org.jrecruiter.model.Job")]
	public class Job {
		
		public function Job() {
			
		    var id:Number;
			var businessName:String;
			var jobTitle:String;
			var updateDate:Date;
			var usesMap:Boolean;
			var latitude:Number;
			var longitude:Number;
			
		}

	}

}