package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.StudentDTO;

public interface MailView extends IsWidget{

	void setStudent(StudentDTO owner);
}
