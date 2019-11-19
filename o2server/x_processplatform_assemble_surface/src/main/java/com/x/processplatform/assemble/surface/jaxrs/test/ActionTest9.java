package com.x.processplatform.assemble.surface.jaxrs.test;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoFile;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.base.core.project.tools.DocumentTools;

class ActionTest9 extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger(ActionTest9.class);

	ActionResult<Wo> execute(EffectivePerson effectivePerson) throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<Wo> result = new ActionResult<>();

			File file = new File("d:/test.doc");

			String name = "test.doc";

			String pdfName = "test.pdf";

			byte[] bytes = DocumentTools.toPdf(name, FileUtils.readFileToByteArray(file), "abcd");

			Wo wo = new Wo(bytes, this.contentType(false, pdfName), this.contentDisposition(false, pdfName));

			result.setData(wo);

			return result;
		}
	}

	public static class Wo extends WoFile {

		public Wo(byte[] bytes, String contentType, String contentDisposition) {
			super(bytes, contentType, contentDisposition);
		}

	}

}