package org.jrecruiter.model {
	import mx.binding.utils.BindingUtils;
	
	
	[Bindable]
	[RemoteClass(alias="org.jrecruiter.model.Job")]
	public class Job {
		
		public function Job() {
			
			public var id:Number;
			public var businessName:String;
			public var jobTitle:String;
			public var updateDate:Date;
			public var usesMap:Boolean;
			public var latitude:Number;
			public var longitude:Number;
			
		}

	}

}