package com.nsi.kanban.server.dao.managers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Managers {
	
	public static KanbanCardManager getKanbanCardManager()
	{
		KanbanCardManager manager = new KanbanCardManager();
		
//		
//		Field factory = null;
//		Field dao = null;
//		
//		for(Field field : manager.getClass().getFields())
//		{
//			if( field.getName() == "factory" )
//				factory = field;
//			if( field.getName() == "dao" )
//				dao = field;
//		}
//		Method getKanbanCardDAO =null;
//		try {
//			getKanbanCardDAO = factory.getType().getMethod("getKanbanCardDAO");
//		} catch (NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Object obj = getKanbanCardDAO.invoke(factory.get(manager));
//			
//			dao.set(manager, obj);
//			
//		} catch (IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(factory.getType() + ", " + dao.getType() + "," + getKanbanCardDAO);
//		
		
//		Class factory = null;
//		try {
//			factory = manager.getClass().getField("factory").getType();
//			
//			System.out.println("ima: " + factory.getName());
//		} catch (NoSuchFieldException | SecurityException e1) {
//			e1.printStackTrace();
//		}
//		Method met = null;
//		for(Method m : factory.getMethods())
//		{
//			System.out.println(m.getName());
//			if ( m.getName() == "getKanbanCardDAO" )
//				met = m;
//		}
//		
//		System.out.println("nasao: "+met.toString());
//		
//		try {
//			met.invoke(manager.getClass().getField("factory"));
//		} catch (IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException | NoSuchFieldException
//				| SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for( Field field : manager.getClass().getDeclaredFields())
//		{
////			if( field.getName().equals("dao") )
//			{
//				for(Annotation anno : field.getDeclaredAnnotations())
//				{
//					if(anno.annotationType() == DAOAnnotation.class)
//					{
//						
//						
//					}
//				}
//			}
//		}
		
		
		return manager;
	}
	
}
