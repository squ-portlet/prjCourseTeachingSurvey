/**
 * Project				:	prjCourseTeachingSurvey
 * Organization			:	Sultan Qaboos University | Muscat | Oman
 * Centre				:	Centre for Information System
 * Department			:	Web & E-Services
 * 
 * Author				:	Bhabesh
 *
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	UtilRenderTrack.java
 * Package Name			:	om.edu.squ.squportal.portlet.tsurvey.utility
 * Date of creation		:	Sep 29, 2015  8:55:24 AM
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2015 the original author or authors and Organization.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * 
 */
package om.edu.squ.squportal.portlet.tsurvey.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import om.edu.squ.squportal.portlet.tsurvey.bo.Param;
import om.edu.squ.squportal.portlet.tsurvey.bo.RenderTrack;




/**
 * @author Bhabesh
 *
 */
public class UtilRenderTrack
{
	
	private UtilRenderTrack() {};
	private	static Map<String,RenderTrack> mapTrack 	= 	null;

	

/**
 * 	
 * method name  : getTrack
 * @param request
 * @param actionName
 * @param linkName
 * @return
 * UtilRenderTrack
 * return type  : Map<String,String>
 * 
 * purpose		: Get the details of the links for dynamic breadcrumb / menus
 *
 * Date    		:	Sep 29, 2015 11:14:04 AM
 */
	//public static Map<String,RenderTrack>  getTrack(PortletRequest request, String actionName, String linkName, List<Param> params)
	public static Map<String,RenderTrack>  getTrack(PortletRequest request, String actionName, String linkName, Param... params)
	{
		PortletSession session = request.getPortletSession();
		if(session.getAttribute(Constants.CONST_MAPTRACK)==null)
		{
			mapTrack	=	new LinkedHashMap<String, RenderTrack>();
			session.setAttribute(Constants.CONST_MAPTRACK, mapTrack);
			
		}
		mapTrack	=	(Map<String, RenderTrack>)session.getAttribute(Constants.CONST_MAPTRACK);

		
		if(! isKeyAvailable(actionName))
		{
			RenderTrack	track	=	null;
			//if(null == params || params.isEmpty())
			if(null == params || params.length == 0)
			{
				track	=	new RenderTrack(actionName, linkName);
			}
			else
			{
				track	=	new RenderTrack(actionName, linkName, params);
			}
			mapTrack.put(actionName, track);
			session.setAttribute(Constants.CONST_MAPTRACK, mapTrack);

		}
		else
		{
			removeOtherTrack(actionName);
			session.setAttribute(Constants.CONST_MAPTRACK, mapTrack);
		}
		
		return mapTrack;
	}
	
	
	
	/**
	 * 
	 * method name  : isKeyAvailable
	 * @param actionName
	 * @return
	 * UtilRenderTrack
	 * return type  : boolean
	 * 
	 * purpose		: checking the key
	 *
	 * Date    		:	Sep 29, 2015 11:14:55 AM
	 */
	private static boolean isKeyAvailable(String actionName)
	{
		return mapTrack.containsKey(actionName)?true:false;
	}
	
	/**
	 * 
	 * method name  : removeOtherTrack
	 * @param actionName
	 * UtilRenderTrack
	 * return type  : void
	 * 
	 * purpose		: remove not necessary keys
	 *
	 * Date    		:	Sep 29, 2015 11:15:12 AM
	 */
	private static void removeOtherTrack(String actionName)
	{
		List<String> keys	=	new ArrayList<String>(mapTrack.keySet());
		for(int i=keys.size()-1; i>=0 ; i--)
		{
			RenderTrack	renderTrack	=	mapTrack.get(keys.get(i));
			if(renderTrack.getActionName().equals(actionName))
			{
				break;
			}
			else
			{
				mapTrack.remove(keys.get(i));
			}
		}
		
	}
	
}
