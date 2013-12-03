/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.spicstome.server.services;


import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spicstome.client.hibernate.HibernateManager;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.User;

/**
 * 
 * @author Maxime
 * 
 * This class describe the service on the server side that will perform the hibernate
 * asking and reporting work.
 * It is based on the HibernateManager
 *
 */

public class SpicsToMeServicesImpl extends RemoteServiceServlet implements SpicsToMeServices {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	public User Login(String login, String password) {
	
		User user = HibernateManager.getInstance().login(login, password);

		if(user!=null)
		{
			getThreadLocalRequest().getSession().setAttribute("currentUser", user);
		}
		
		return user;
		
		
	}

	@Override
	public User CurrentUser() {
		
		return (User)getThreadLocalRequest().getSession().getAttribute("currentUser");
	}

	
	
	

	
	
	
	
}
